import FetchTemplate from "utils/FetchTemplate";

export const TryGetArticles = async (keywordId) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + `/hottopic/article/articleList?keyword=${keywordId}`,
            headers: {},
            method: "GET",
        });

        const result = await response.json();
        return result;
    } catch (e) {
        console.log(e);
    }
}



export const TryScrapped = async (accessToken,keywordId,articleId) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/scrap/add',
            method: "POST",
            headers: {
                Authorization :`${accessToken}`
            },
            body: JSON.stringify({
                "keyWord":keywordId,
                "article":articleId,
            })
        });
        const result = await response.json();
        return result
    } catch (e) {
        console.log(e);
    }
}

export const TryGetAllScrapped = async (accessToken) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + `/scrap/scrapList`,
            method: "GET",	
            headers: {
                Authorization: `${accessToken}`
            },
        });

        const result = await response.json();
        return result
    } catch (e) {
        console.log(e);
    }
}