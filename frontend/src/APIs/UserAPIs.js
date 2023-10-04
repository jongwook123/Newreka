import FetchTemplate from "utils/FetchTemplate";

export const TryLogin = async (email, password) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/user/login',
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "email": email,
                "password": password,
            })
        })
        
        const result = await response.json();

        return result;
    } catch (e) {
        console.log(e);
    }
}

export const TrySignup = async (email, name, nickname, password,pwck) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/user/signup',
            method: "POST",
            headers: {},
            body: JSON.stringify({
                "email": email,
                "name": name,
                "nickname": nickname,
                "password": password,
                "pwck": pwck,
            })
        });
        
        const result = await response.json();

        return result;
    } catch (e) {
        console.log(e);
    }
}