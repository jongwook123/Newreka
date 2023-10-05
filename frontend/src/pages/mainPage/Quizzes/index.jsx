import { TrySubmitAnswers } from "APIs/QuizAPIs";
import * as S from "./style";

import { useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from 'react-router-dom';

export default function Quizzes({ quizData, selectedKeyword }) {
  const accessToken = useSelector(state => state.user.accessToken);
  const [userAnswers, setUserAnswers] = useState([]);
  const [submitResult, setSubmitResult] = useState(null);
  const navigate = useNavigate();

  const handleOptionClick = (quizIndex, optionKey) => {
    setUserAnswers(prev => {
      const newAnswers = [...prev];
      newAnswers[quizIndex] = optionKey;
      return newAnswers;
    });
  };

  const handleSubmit = async () => {
    if (userAnswers.length < 3) {
      alert('정답을 모두 체크해주세요');
    } else {
      try {
        let response = await TrySubmitAnswers(accessToken, quizData[0].quizId, userAnswers[0][6], quizData[1].quizId, userAnswers[1][6], quizData[2].quizId, userAnswers[2][6]);
        setSubmitResult(response.result);
      }
      catch (e) {
        console.error(e);
        alert('Error while submitting answers');
      }

    }
  };

  const resetQuiz = () => {
    setUserAnswers([]);
    setSubmitResult(null);
  };

  const toMyPage = () => {
    navigate('/mypage', { state: { scrollToTop: true } });
  }

  console.log(quizData)

  if (submitResult === null) {
    return (
      <div>
        {quizData && (
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
          </>)
        }
      </div>);
  } else if (submitResult === false) {
    return (<S.ResultSection>
      <h1>틀렸습니다.</h1><br />
      <S.SubmitButton onClick={() => resetQuiz()}>다시 풀기</S.SubmitButton></S.ResultSection>);
  } else if (submitResult === true) {
    return (<S.ResultSection><h1>정답입니다. <br /> "{selectedKeyword}" 키워드를 획득하셨습니다!</h1><br />
      <S.SubmitButton onClick={() => toMyPage()}>확인하러 가기</S.SubmitButton></S.ResultSection>);
  } else {
    return (<S.ResultSection>
      <h1>이미 푼 문제입니다.</h1>
    </S.ResultSection>)
  }

};
