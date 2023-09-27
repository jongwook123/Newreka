import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';



export default function MainPage() {
    const isLoggedIn = false; // Replace with your actual login logic
    const menuname = isLoggedIn ? 'My page' : 'Login';
    



    return (
        <S.Main>
             <Header menuname={menuname}>
            </Header>
            <S.BodySection>
                <S.Body>
                    <h2>HOT 10</h2>
                    <WordCloudPage />
                </S.Body>
                <S.Body>
                    <h2>Selected Keyword</h2>
                    <MainPageTabs />
                </S.Body>
            </S.BodySection>
            <Footer />
        </S.Main>
    )

}
