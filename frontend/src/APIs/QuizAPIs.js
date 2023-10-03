import FetchTemplate from "utils/FetchTemplate";

export const TryGetQuiz = async (accessToken, keywordIdx) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + `/quiz/quizList?keyword=${keywordIdx}`,
            method: "GET",	
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
        });

        const result = await response.json();
        console.log(result)
        
        return result
    } catch (e) {
        console.log(e);
    }
}