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