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
### Docker 설치


자세한 사항은 [공식문서](https://docs.docker.com/engine/install/ubuntu/)를 참조해주세요.<br />
**repository index 갱신하기**
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

**Docker Engine 설치**
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
Linux는 docker-compose를 따로 설치해줘야합니다.
이 프로젝트에서는 v1을 설치할 예정입니다. 자세한 사항은 [공식문서](https://docs.docker.com/compose/install/)를 참조해주세요.
<br />
**Docker compose 다운**
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

**openjdk 11 설치**
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

**gradle 설치 및 빌드**
```bash
$ sdk install gradle 6.8
$ ./gradlew build ./server/build.gradle
```
<br />

### Docker-compose 실행


## 2. 배포 시 특이사항
http://[도메인주소]/main 페이지가 시작페이지입니다.


## 3. DB 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록
- DB 생성 파일
  ./docker-compose.yml
<br />

- 주요 계정 및 프로퍼티 정의 파일
  ./server/api-module/src/main/resources/application-real.yml



//////////////////////////////////////////////////////////////////////////////////
# 프로젝트에서 사용된 외부 서비스
1. Teachable Machine

///////////////////////////////
2. Stomp
3. 비디오
4. AI Hub



할 거
DB 덤프파일 최신본
시연 시나리오(PPT로?)
 -> 시연 순서에 따른 site 화면별, 실행별(클릭위치 등) 상세설명
