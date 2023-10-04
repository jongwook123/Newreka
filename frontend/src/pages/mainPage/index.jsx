import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';
import { useSelector } from 'react-redux';
import { useState } from 'react';
import { useEffect } from 'react';
import { GetKeyword, GetTimeKeyword } from 'APIs/KeywordAPIs';

export default function MainPage() {
  const accessToken = useSelector(state => state.user.accessToken);
  const isLoggedIn = !!accessToken;
  const menuname = isLoggedIn ? 'My page' : 'Login';
  const [selectedKeyword, setSelectedKeyword] = useState('');
  const [data, setData] = useState(null);
  
  const getCurrentTimeFormatted = () => {
    const currentDate = new Date();
    let minutes = currentDate.getMinutes();
  
    // Round down to the nearest multiple of 10
    minutes = Math.floor(minutes / 10) * 10;
  
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const day = String(currentDate.getDate()).padStart(2, '0');
    const hours = String(currentDate.getHours()).padStart(2, '0');
    const formattedMinutes = String(minutes).padStart(2, '0');
  
    return `${year}${month}${day}${hours}${formattedMinutes}`;
  };

  const fetchData = async () => {
    try {
      const fetchedData = await GetKeyword();
      setData(fetchedData);
    } catch (error) {
      console.log(error);
    }
  };

  const formattedTime = getCurrentTimeFormatted()
  // const formattedTimeAsNumber = parseInt(formattedTime, 10);
  const fetchTimeData = async () => {
    try {
      const fetchedData = await GetTimeKeyword(formattedTime);
      setData(fetchedData);
    } catch (error) {
      console.log(error);
    }
  };

  

  useEffect(() => {
    // 함수를 만들어서 현재 시간의 분 끝자리가 2일 때 fetchData를 호출하도록 설정
    
    const fetchDataOn2ndMinute = () => {
      const currentMinute = new Date().getMinutes();
      if (currentMinute % 10 === 2) {
        fetchData();
      }
    };
    
    fetchTimeData();
    // 최초 실행

    // 1분마다 fetchDataOn2ndMinute를 호출하여 분 끝자리가 2일 때 fetchData 호출
    const intervalId = setInterval(fetchDataOn2ndMinute, 60000);

    // 컴포넌트가 언마운트될 때 clearInterval하여 타이머 정리
    return () => clearInterval(intervalId);
  }, []);
  

  const handleWordClick = (selectedWord) => {
    setSelectedKeyword(selectedWord);
  }

  return (
    <S.Main>
      <Header menuname={menuname} />
      <S.BodySection>
        <S.Body>
          <h2>HOT 10</h2>
          {data && <WordCloudPage onWordClick={handleWordClick} data={data} />}
        </S.Body>
        <S.Body id="body2">
          <h2>{selectedKeyword}</h2>
          {data && <MainPageTabs selectedKeyword={selectedKeyword} data={data} />}
        </S.Body>
      </S.BodySection>
      <Footer />
    </S.Main>
  );
}