FROM node:14.17.5 as builder

# 작업 폴더를 만들고 npm 설치
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY ./package*.json /usr/src/app/
RUN npm install --silent
RUN npm install react-scripts@2.1.3 -g --silent

# 소스를 작업폴더로 복사하고 빌드
COPY . /usr/src/app
RUN npm run build


FROM nginx:1.13.9-alpine

# Timezone configs
RUN ln -sf /usr/share/zoneinfo/Asiz/Seoul /etc/localtime
ENV TZ Asia/Seoul

# 위에서 생성한 앱의 빌드산출물을 nginx의 샘플 앱이 사용하던 폴더로 이동
COPY --from=builder /usr/src/app/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/nginx.conf