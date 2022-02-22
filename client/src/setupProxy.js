const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://i6d203.p.ssafy.io:8185/', 
      target: 'https://i6d203.p.ssafy.io:8185/ws-stomp/', 
      // 프론트엔드에서 벡엔드로 줄때 타겟을 설정하고 5000번으로 주겠다.(나중에 우리 백엔드 port번호로 바꿔야함)
      changeOrigin: true,
    })
  );
};