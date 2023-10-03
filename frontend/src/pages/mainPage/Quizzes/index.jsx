import * as S from "./style";

import { useState } from "react";

export default function Quizzes({ quizData }) {
  const [userAnswers, setUserAnswers] = useState([]);

  console.log(quizData)

  const handleOptionClick = (quizIndex, optionKey) => {
    setUserAnswers(prev => {
      const newAnswers = [...prev];
      newAnswers[quizIndex] = optionKey;
      return newAnswers;
    });
  };

  const handleSubmit = () => {
    console.log(userAnswers);
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
          <button onClick={handleSubmit}>제출</button>
        </>
      ) : (
        <p>문제 준비중입니다...</p>
      )}

    </div>
  );
}