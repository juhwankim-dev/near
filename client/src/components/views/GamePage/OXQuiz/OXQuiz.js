import React from 'react';

const OXQuiz = () => {

    const iframepart = () => {
        return {
            __html: '<iframe height="300" width="700"  frameborder="0" scrolling="no" src="test.html" width="100%" heigth="100%"</iframe>',
        };
    };
  
    return (
        <div
            style={{ margin: 'auto', position: 'relative', width:'100%', height: '100%', overflow:'hidden'
        }}
        dangerouslySetInnerHTML={iframepart()}
        />
    );
};

export default OXQuiz;
