# 빌드 및 배포

## 0. 환경

**💎 FrontEnd**
- 언어 : JavaScript, SCSS
- 프레임워크 : Node.js
- 라이브러리 : React
- IDE : Visual Studio Code 1.64
<br />

**👑 BackEnd**
- 언어 : JAVA 11
- 프레임워크 : SpringBoot 2.3.2, gradle 6.8
- 인터페이스 : JPA
- IDE : IntelliJ IDEA 2021.3.2
<br />

**📕 DB**
- MariaDB 10.6.5
<br/>

**🏆 Android**
- 언어 : Kotlin
- IDE : Android Studio Arctic Fox
<br />

**🌳 운영체제**
- Ubuntu 20.04
- Docker 20.10.12
- Jenkins
<br />

## 1. 빌드 및 배포 방법
### 프로젝트 clone
```bash
$ git clone https://lab.ssafy.com/s06-webmobile4-sub2/S06P12D203.git
```

### Docker 설치

자세한 사항은 [공식문서](https://docs.docker.com/engine/install/ubuntu/)를 참조해주세요.
<br /><br />
**repository index 갱신하기**<br />
`apt` 패키지를 업데이트 한 후 도커 다운을 위해 필요한 패키지를 설치합니다.

```bash
$ sudo apt-get update

$ sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
```
공식 GPG key 추가해줍니다.
```bash
$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
```
다음 명령을 사용하여 안정적인 repository를 설정합니다.
```bash
$ echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```
<br />

**Docker Engine 설치**<br />
`apt` 패키지를 업데이트하고 Docker 최신 버전을 설치합니다.
```bash
$ sudo apt-get update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
```
hello-word 이미지를 실행하여 도커가 잘 설치되었는지 확인합니다.
```
$ sudo docker run hello-world
```
<br />

### Docker compose 설치
Linux는 docker-compose를 따로 설치해줘야합니다.<br />
이 프로젝트에서는 v1을 설치할 예정입니다. 자세한 사항은 [공식문서](https://docs.docker.com/compose/install/)를 참조해주세요.
<br /><br />
**Docker compose 다운**<br />
최신의 안정적 배포 버전을 다운로드 합니다.
```bash
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```
다운받은 docker-compose 파일에 실행 권한을 부여합니다.
```bash
$ sudo chmod +x /usr/local/bin/docker-compose
```
docker compose가 설치되었는지 확인합니다.
```bash
$ docker-compose --version
# docker-compose version 1.29.2, build 1110ad01 와 같이 출력되어야 정상.
```
> 🚨 **Note**
> 
> 만약 docker-compose를 실행하는 것에 실패했다면, 경로를 체크합니다.
> ```bash
> $ sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
> ```
<br />

### gradle 설치 및 build
spring의 jar 파일을 생성하기 위해 gradle을 설치 및 빌드해주어야 합니다.

**openjdk 11 설치**<br />
`apt` 패키지를 업데이트하고 `openjdk-11-jdk`를 설치합니다.
```bash
$ sudo apt-get update && sudo apt-get upgrade
$ sudo apt-get install openjdk-11-jdk
```
설치되었는지 확인합니다.
```bash
$ java -version
# openjdk version "11.0.9.1" 2020-11-04
# OpenJDK Runtime Environment (build 11.0.9.1+1-Ubuntu-0ubuntu1.20.04)
# OpenJDK 64-Bit Server VM (build 11.0.9.1+1-Ubuntu-0ubuntu1.20.04, mixed mode, sharing)

$ javac -version
# javac 11.0.9.1
```
<br />

### ssl 인증서 발급
✅ `./client/nginx.conf` 파일에서 server_name을 각자의 도메인 주소로 바꿔주세요.<br />
Docker로 인증서를 발급받습니다.
```bash
docker run -it --rm --name cert_tmp -p 80:80 -v ../certbot/conf:/etc/letsencrypt certbot/certbot certonly --standalone -d [도메인 주소] -m [이메일]
```

두 번 y를 입력해주세요. 다음과 같이 뜨면 인증서 발급에 성공하였습니다.
``` bash
Saving debug log to /var/log/letsencrypt/letsencrypt.log

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Please read the Terms of Service at
https://letsencrypt.org/documents/LE-SA-v1.2-November-15-2017.pdf. You must
agree in order to register with the ACME server. Do you agree?
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(Y)es/(N)o: y

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Would you be willing, once your first certificate is successfully issued, to
share your email address with the Electronic Frontier Foundation, a founding
partner of the Let's Encrypt project and the non-profit organization that
develops Certbot? We'd like to send you email about our work encrypting the web,
EFF news, campaigns, and ways to support digital freedom.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(Y)es/(N)o: y
Account registered.
Requesting a certificate for [도메인주소]

Successfully received certificate.
Certificate is saved at: /etc/letsencrypt/live/[도메인주소]/fullchain.pem
Key is saved at:         /etc/letsencrypt/live/[도메인주소]/privkey.pem
This certificate expires on 2022-05-18.
These files will be updated when the certificate renews.

NEXT STEPS:
- The certificate will need to be renewed before it expires. Certbot can automatically renew the certificate in the background, but you may need to take steps to enable that functionality. See https://certbot.org/renewal-setup for instructions.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
If you like Certbot, please consider supporting our work by:
 * Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
 * Donating to EFF:                    https://eff.org/donate-le
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
```

chain.pem과 fullchain.pem으로 keystore.p12 파일을 생성합니다.
```bash
$ cd /home/ubuntu/certbot/conf/live/[도메인주소]/
$ openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name [이름] -CAfile chain.pem -caname root
# Enter Export Password: # 비밀번호 설정
# Verifying - Enter Export Password: # 비밀번호 재확인
```

keystore.p12파일을 server 프로젝트에 저장합니다.
```bash
$ cd /home/ubuntu/
$ cp /keystore.p12 /server/api-module/src/main/resources
```
<br />

> 🚨 application-real.yml 파일 수정
> `/server/api-module/src/main/resources` 내 application-real.yml 파일의 ssl 설정을 수정해줍니다.
> ```bash
> server:
>
>   # ...
>
>   ssl:
>     enabled: true
>     key-store-type: PKCS12
>     key-store: classpath:/keystore.p12
>     key-store-password: [비밀번호]
>     key-alias: [이름]
>   trust:
>     store: classpath:/keystore.p12
>     store.password: [비밀번호]
> ```

**gradle 설치 및 빌드**
```bash
$ sdk install gradle 6.8
$ ./gradlew build ./server/build.gradle
```
<br />

### Docker-compose 실행
docker compose로 애플리케이션을 실행합니다.
```bash
docker-compose up -d
```
<br />

## 2. 배포 시 특이사항
`https://[도메인주소]/main`이 시작페이지입니다.


## 3. DB 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록
- DB 생성 파일<br />
  ./docker-compose.yml
<br />

- 주요 계정 및 프로퍼티 정의 파일<br />
  ./server/api-module/src/main/resources/application-real.yml

- DB dump.sql  
  [Near_Dump.sql](Near_Dump.sql)

<br /><br /><br />


***


# 프로젝트에서 사용된 외부 서비스
### [Teachable Machine↗](https://teachablemachine.withgoogle.com/)
머신러닝 모델을 쉽고 빠르고 간단하게 만들 수 있도록 제작된 웹 기반 도구<br />
머신러닝 코드를 작성하지 않고도 컴퓨터가 이미지, 사운드, 포즈를 인식하도록 컴퓨터를 학습시킬 수 있습니다.<br />
학습시킨 모델을 프로젝트, 사이트, 앱 등에서 사용하면 됩니다.<br />
이미지 프로젝트로 모델을 생성하였습니다.

<br />

### [AI Hub↗](https://aihub.or.kr/)
AI Hub에서 제공하는 수어 영상을 사용하였습니다.<br />
지숫자, 지문자의 데이터 영상 생성을 통해 한국수어 인식 인공지능 기술 및 서비스 개발에 활용 가능한 총 536,000개의 영상 데이터 제공합니다.<br />
사이트 가입 후  `개방 데이터` > `음성/자연어` > `수어 영상 다운로드` 를 통해 다운로드.

<br /><br />


# 시연 시나리오
## 웹
### 메인화면
메인화면에서 네비바에 드롭다운을 열어 회원가입으로 이동.<br />
(회원가입 중복검사/유효성 검사) 우선 해보고 시간 테스트<br />
### 로그인 
로그인 페이지에서 아이디와 비밀번호를 입력 후 sign in 버튼을 눌러 로그인을 완료.<br />
로그인을 완료하면 자동으로 메인페이지로 이동.<br />
### 메인화면
1) 이때 play가 작성되어 있는 스크롤 버튼을 눌러 하단으로 이동.<br />
2)이동 후 좌측에 지어 게임 버튼에 가져다 대고 메인 페이지에서 바로 지어 게임으로 들어갈 수 있다고 설명을 한 후 우측에 있는 수어 게임 시작 버튼에 마우스를 가져다 댄 후 마찬가지로 수어 게임(OX 게임)을 할 수 있음을 설명.<br />
3) study 스크롤을 클릭하여 스크롤 한번 아래로 이동.<br />
4) 아까와 동일하게 좌측에서 지어 학습으로 바로 이동할 수 있고 우측에서 수어 학습을 할 수 있다고 설명.<br />
5)지어 학습 버튼 클릭<br />

### 지어 학습 페이지
1)지어 학습 페이지로 이동하여 자음과 모음 버튼을 누르며 자음/모음을 학습할 수 있는 자료가 있다는 것을 설명.<br />
2)이후 지어의 자음 페이지에서 ㄱ을 눌러 모달로 이동하고 설명을 보여줌.<br />
3)다음 버튼을 눌러 ㄴ에 대한 설명을 보여주고 창 닫기 버튼을 눌러 창을 닫기.<br />

### 수어 학습 페이지
네비바를 사용하여 수어 학습으로 이동.<br />
1) 수어페이지에서 00 이라는 첫번째 단어를 클릭.<br />
2) 좌측에 수어 애니메이션 동영상을 통하여 움직이는 동작으로 학습 할 수 있고 자세한 동작 설명은 우측에 이미지로 다시 한번 확인 할 수 있다.<br />
3)아래에 수어의 각각의 동작을 의미하는 예를 들어 솜+ 사탕이 있고 그 아래에 솜사탕에 대한 뜻이 있다.<br />
4) 상세페이지에서 (단어장 기능을 설명하며) 단어장 버튼하면 단어장에 해당하는 단어들이 저장되어 반복 학습을 가능하게 합니다. 라고 설명 (첫페이지 단어장 1개)<br /> 
6)단어를 여러개 넣어보기<br />
7) 목록 버튼 클릭 <br />
8)하나는 근처에 있는 단어(단어 1개) -2개<br />
9)목록으로 돌아옴(시간 되면 페이지네이션 눌러서 다른 페이지에 단어 1개 추가 다양한 의미 학습 가능함을 어필) (여기까지 단어 총3개가 단어장에 있다)<br />
**다 1페이지에 있는 단어여야함.**

### 단어장 페이지
단어장으로 네비바를 사용하여 이동하고 단어를 삭제.<br />
1) 단어장에서 단어 클릭하면 세부 사항 볼 수 있고 하나씩 삭제 가능<br />
2)학습을 충분히 했다고 생각하면 단어장에 있는 단어들을 한꺼번에 삭제할 수 있습니다. 하면서 이제 전체 삭제버튼 클릭<br />

### 게임
1) 지문자 단어 게임 시연<br />
2) OX 게임 시연 (네비바에서 OX 퀴즈 누름)<br />

## 모바일
### 홈 화면
앱을 실행시키면 가장 먼저 홈 화면이 보인다. 서비스 소개를 볼 수 있음.<br />
여기서 화살표 방향대로 위로 밀면 저희 서비스 소개를 볼 수 있습니다.<br />
### 지문자 학습 화면
전체, 자음이나 모음 따로 옵션으로 지문자 리스트를 볼 수 있음.<br />
단어를 누르면 상세페이지로 크게 보기 가능.<br />
### 수어
수어 단어 리스트 보여줌.<br />
단어를 눌러 상세페이지 열 수 있다. 상단에는 동영상, 하단에는 단어의 뜻과 의미 해석, 그림이 있는 카드뷰가 존재. 옆으로 밀면 자세히 동작 묘사한 글 보기 가능.<br />
화면을 아래로 밀면 유튜브처럼 영상이 작게 표시됨.<br />
하단의 북마크는 단어장을 위한 기능. 단어장은 로그인이 필요한 기능.<br />
### 로그인
마이페이지 > 로그인 접속.<br />
로그인<br />
로그인된 마이페이지를 보여줌<br />
### 단어장
수어페이지에서 몇 개 아무단어나 상세페이지 열고 추가<br />
단어장을 보여줌.<br />
상세페이지 열리는 것을 보여줌. 상세페이지에서 삭제하는 모습을 보여줌.<br />
단어장에서 단어 몇개 선택해서 삭제하는 모습 보여줌.(다중 삭제)<br />
단어장 전체 삭제 보여줌.<br />
### OX 퀴즈게임
게임은 로그인한 사용자만 할 수 있다는 걸 설명.<br />
OX 퀴즈 게임 시작. 1번 문제는 맞히고 2번 문제는 틀릴 것.<br />
### 지문자 낱말 퀴즈
모바일에서는 지문자 낱말 퀴즈 게임에 멀티 플레이 모드를 지원. 최대 4명까지 같이 할 수 있음.<br />
시연자 2명. 2개의 모바일 화면을 보여줌.<br />
시연자 1이 방 생성. 생성된 방에 시연자 2가 입장.<br />
시연자 1이 캐릭터 변경. 시연자 2 캐릭터 변경.<br />
시연자 1만 게임을 시작할 수 있음. 시연자 1이 게임 시작 버튼 누르고 게임 시작<br />
시연자 2만 정답을 맞춤. 시연자 1은 엔터를 누르면 답이 입력된다는 점. 1문제 당 정답 입력 기회는 1번이라는 걸 설명<br />
결과 화면을 보여줌. 5초 뒤엔 방이 사라지고 방 목록 화면으로 돌아옴.<br />
