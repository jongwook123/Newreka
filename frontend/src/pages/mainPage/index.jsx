import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';
import { useSelector } from 'react-redux';
import { useState, useEffect, useRef } from 'react';
import { GetKeyword, GetTimeKeyword } from 'APIs/KeywordAPIs';
import TimeBar from 'component/timebar';

export default function MainPage() {
  const accessToken = useSelector(state => state.user.accessToken);
  const isLoggedIn = !!accessToken;
  const menuname = isLoggedIn ? 'My page' : 'Login';
  const [selectedKeyword, setSelectedKeyword] = useState('');
  const [data, setData] = useState({ quizList: [] });
  const [selectedTime, setSelectedTime] = useState(null);

  // 렌더링시 스크롤 최상단
  useEffect(() => {
    window.onbeforeunload = function pushRefresh() {
      window.scrollTo(0, 0);
    };
  }, []);
  // 최신 키워드 불러와서 저장
  const fetchData = async () => {
    try {
      const fetchedData = await GetKeyword();
      setData(fetchedData);

    } catch (error) {
      console.log(error);
    }
  };

  // 현재 시간의 분 끝자리가 2일 때 fetchData를 호출하도록 설정
  const getCurrentTimeFormatted = () => {
    const currentDate = new Date();
    let minutes = currentDate.getMinutes();

    let adjustedMinutes;

    if (minutes >= 2 && minutes <= 11) {
      adjustedMinutes = '00';
    } else if (minutes >= 12 && minutes <= 21) {
      adjustedMinutes = '10';
    } else if (minutes >= 22 && minutes <= 31) {
      adjustedMinutes = '20';
    } else if (minutes >= 32 && minutes <= 41) {
      adjustedMinutes = '30';
    } else if (minutes >= 42 && minutes <= 51) {
      adjustedMinutes = '40';
    } else {
      adjustedMinutes = '50';
    }

    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    const day = String(currentDate.getDate()).padStart(2, '0');
    const hours = String(currentDate.getHours()).padStart(2, '0');

    return `${year}${month}${day}${hours}${adjustedMinutes}`;
  };

  const formattedTime = getCurrentTimeFormatted()

  // 해당 시간 키워드 불러와서 저장
  const fetchTimeData = async () => {
    try {
      const fetchedData = await GetTimeKeyword(formattedTime);
      setData(fetchedData);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    const fetchDataOn2ndMinute = () => {
      const currentMinute = new Date().getMinutes();
      if (currentMinute % 10 === 2) {
        fetchData();
      }
    };

    // 최초 실행
    fetchTimeData();

    // 1분마다 fetchDataOn2ndMinute를 호출하여 분 끝자리가 2일 때 fetchData 호출
    const intervalId = setInterval(fetchDataOn2ndMinute, 60000);

    // 컴포넌트가 언마운트될 때 clearInterval하여 타이머 정리
    return () => clearInterval(intervalId);
  }, []);


  // timebar에서 시간 클릭시 selectedTime의 format 변경 후 해당 시간 키워드 불러와서 저장
  useEffect(() => {
    if (selectedTime) {
      const date = new Date(selectedTime);
      date.setHours(date.getHours());

      const year = date.getFullYear(); // 년도
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 월 (월은 0부터 시작하므로 +1 필요)
      const day = String(date.getDate()).padStart(2, '0'); // 일
      const hours = String(date.getHours()).padStart(2, '0'); // 시간
      const minutes = String(date.getMinutes()).padStart(2, '0'); // 분

      const formattedDate = `${year}${month}${day}${hours}${minutes}`;
      GetTimeKeyword(formattedDate)
        .then(fetchedData => setData(fetchedData))
        .catch(error => console.log(error));
    }
  }, [selectedTime]);

  // 선택된 키워드 저장
  const handleWordClick = (selectedWord) => {
    setSelectedKeyword(selectedWord);
  }

  // 타임바에서 선택시 스크롤 위로 이동
  const body1Ref = useRef(null);

  const handleTimeSlotClick = (time) => {
    setSelectedTime(time);
    if (body1Ref.current) {
      body1Ref.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <S.Main>
      <Header menuname={menuname} />
      <S.BodySection>
        <S.Body id="body1" ref={body1Ref}>
          <h2>TOP 10</h2>
          {data && data.quizList && data.quizList.length > 0 &&
            <>
              <WordCloudPage onWordClick={handleWordClick} data={data} />
              {formattedTime && <TimeBar formattedTime={formattedTime} setSelectedTime={handleTimeSlotClick} selectedTime={selectedTime} />}
            </>
          }

        </S.Body>
        {selectedKeyword && (
          <S.Body id="body2">
            <h2>{selectedKeyword}</h2>
            {data && <MainPageTabs selectedKeyword={selectedKeyword} data={data} />}
          </S.Body>
        )}
      </S.BodySection>
      <Footer />
    </S.Main>
  );
}