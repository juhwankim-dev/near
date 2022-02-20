import React, { useEffect, useState } from "react";


export const ChatPresenter = () => {

    const [contents, setContents] = useState([]);
    const [message, setMessage] = useState('');
    const [username, setUsername] = useState('');

    // const handleEnter = (username, value) => {
    // }

    return (
        <>
        <div className={"chat-box"}>
            <div className='header'>
            유저이름 : 
            <input
                style={{flex : 1}}
                value={username}
                onChange={e=>setUsername(e.target.value)}
            />
            </div>
            <div className={"contents"}>
            {contents.map((message) => (
                <div> {message.username} : {message.content} </div>
            ))}
            </div>
            <div>
            <input
                placeholder="input your messages..."
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                // onSearch={(value) => handleEnter(username,value)}
                enterButton={"Enter"}
            />
            </div>
        </div>
        </>
        );
        
};
