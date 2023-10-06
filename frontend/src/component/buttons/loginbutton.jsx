import React from "react";
import { useHistory } from 'react-router-dom';

const LoginButton = () => {
    const history = useHistory();

    const handleClick = () => {
        history.push('http://localhost:8080');
    };

    return (
        <button onClick={handleClick}>
            Click me to go to http://localhost:8080
        </button>
    );
};

export default LoginButton;