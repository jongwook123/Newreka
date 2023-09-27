import * as S from "./style";

import { useState } from "react";

export default function Quizzes(props) {
  const dummyQuizzes = {
    "quiz": [
      {
        "question": "윤미향 무소속 의원이 항소심에서 징역 1년 6개월에 집행유예 3년을 선고받은 혐의는 무엇인가요?",
        "options": {
          "a": "정의기억연대 후원금 횡령",
          "b": "위안부 피해자 할머니들을 이용한 사리사욕",
          "c": "국민의힘 비박",
          "d": "항소심 판결 공개"
        },
        "answer": "a"
      },
      {
        "question": "윤미향 의원이 이재명 민주당 대표에 대해 요구한 것은 무엇인가요?",
        "options": {
          "a": "윤 의원을 국회에 들이고 아무 죄가 없는 것처럼 두둔",
          "b": "사법부에 대한 비판",
          "c": "면죄부를 받아 사퇴",
          "d": "판사 탄핵"
        },
        "answer": "c"
      },
      {
        "question": "윤미향 의원에게 징역 1년 6개월에 집행유예 3년을 선고한 재판부의 이유는 무엇인가요?",
        "options": {
          "a": "국민의 민심에 반하여",
          "b": "기대를 저버리고 횡령 범죄를 저질렀기 때문",
          "c": "무죄 판결을 내린 1심 판결에 비해 형량을 감경",
          "d": "판결이 주어진 날짜와 연관이 있는 이유"
        },
        "answer": "b"
      }
    ]
  }

  const [userAnswers, setUserAnswers] = useState(Array(dummyQuizzes.quiz.length).fill(null));

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
      {dummyQuizzes.quiz.map((quiz, index) => (
        <S.EachQuiz key={index}>
          <h2>{index + 1}. {quiz.question}</h2>
          {Object.entries(quiz.options).map(([key, value]) => (
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
                {value}
              </label>
            </S.Option>
          ))}
        </S.EachQuiz>
      ))}
      <button onClick={handleSubmit}>제출</button>
    </div>
  );
}