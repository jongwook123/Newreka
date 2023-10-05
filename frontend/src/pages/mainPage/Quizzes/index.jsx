import { TrySubmitAnswers } from "APIs/QuizAPIs";
import * as S from "./style";

import { useState } from "react";
import { useSelector } from "react-redux";

export default function Quizzes({ quizData }) {
  const accessToken = useSelector(state => state.user.accessToken);
  const [userAnswers, setUserAnswers] = useState([]);

  const handleOptionClick = (quizIndex, optionKey) => {
    setUserAnswers(prev => {
      const newAnswers = [...prev];
      newAnswers[quizIndex] = optionKey;
      return newAnswers;
    });
  };

  const handleSubmit = () => {
    if (userAnswers.length < 3) {
      alert('정답을 모두 체크해주세요');
    } else {
      TrySubmitAnswers(accessToken, quizData[0].quizId, userAnswers[0][6], quizData[1].quizId, userAnswers[1][6], quizData[2].quizId, userAnswers[2][6])
    }
  };


  return (
    <div>
      {quizData && quizData.length > 0 ? (
        <>
          {quizData.map((quiz, index) => (
            <S.EachQuiz key={index}>
              <h2>{index + 1}. {quiz.title}</h2>
              {["answer1", "answer2", "answer3", "answer4"].map((key) => (
                <S.Option
                  key={key}
                  isSelected={userAnswers[index] === key}
                >
                  <label>
                    <input
                      type="radio"
                      name={`option${index}`}
                      value={key}
                      checked={userAnswers[index] === key}
                      onChange={() => handleOptionClick(index, key)}
                    />
                    {quiz[key]}
                  </label>
                </S.Option>
              ))}
            </S.EachQuiz>
          ))}
          <S.Button>
            <S.SubmitButton onClick={handleSubmit}>
              <p>제출</p>
            </S.SubmitButton>
          </S.Button>
        </>
      ) : (
        <p>문제 준비중입니다...</p>
      )}

    </div>
  );
}