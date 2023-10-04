import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';
import { useSelector } from 'react-redux';
import { useState } from 'react';
import { useEffect } from 'react';
import { GetKeyword } from 'APIs/KeywordAPIs';


export default function MainPage() {
  const accessToken = useSelector(state => state.user.accessToken);
  const isLoggedIn = !!accessToken;
  const menuname = isLoggedIn ? 'My page' : 'Login';

  const [selectedKeyword, setSelectedKeyword] = useState('');
  const [data, setData] = useState({});

  useEffect(() => {
    (async () => {
      try {
        const fetchedData = await GetKeyword();
        setData(fetchedData);
      } catch (error) {
        console.log(error);
      }
    })();
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