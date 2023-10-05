import FetchTemplate from "utils/FetchTemplate";

export const TryGetQuiz = async (accessToken, keywordIdx) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + `/quiz/quizList?keyword=${keywordIdx}`,
            method: "GET",	
            // headers: {
            //     Authorization: `Bearer ${accessToken}`
            // },
        });

        const result = await response.json();
        
        return result
    } catch (e) {
        console.log(e);
    }
}

export const TrySubmitAnswers = async (accessToken,quizId1,quiz1answer,quizId2,quiz2answer,quizId3,quiz3answer) => {
    try {
        const response = await FetchTemplate({
            path: process.env.REACT_APP_BASE_SERVER + '/quiz/compare',
            method: "POST",
            headers: {
                Authorization :`${accessToken}`
            },
            body: JSON.stringify({
                "quizId1": quizId1,
                "quiz1answer": quiz1answer,
                "quizId2": quizId2,
                "quiz2answer": quiz2answer,
                "quizId3": quizId3,
                "quiz3answer": quiz3answer
            })
        });
        const result = await response.json();
        console.log(result)
        return result
    } catch (e) {
        console.log(e);
    }
}