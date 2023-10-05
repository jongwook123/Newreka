import FetchTemplate from "utils/FetchTemplate";

export const GetKeyword = async () => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/hottopic/keyword/keyWordList',
            headers: {},
            method: "GET",
        });

        const result = await response.json();
        return result;

    } catch (e) {
        console.log(e);
    }
}

export const GetTimeKeyword = async (formattedTime) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + `/hottopic/keyword/keyWordList/${formattedTime}`,
            headers: {},
            method: "GET",
        });

        const result = await response.json();
        return result;

    } catch (e) {
        console.log(e);
    }
}

export const GetCountKeyword = async (accessToken) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/quizState/myKeyWord',
            headers: {
                Authorization :`${accessToken}`
            },
            method: "GET",
        });

        const result = await response.json();
        return result;

    } catch (e) {
        console.log(e);
    }
}