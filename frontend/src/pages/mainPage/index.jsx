import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';
import { useSelector } from 'react-redux';


export default function MainPage() {
    const accessToken = useSelector(state => state.user.accessToken); // Access accessToken from redux state
    const isLoggedIn = !!accessToken; // Set isLoggedIn based on the existence of accessToken

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
                <S.Body id="body2">
                    <h2>Selected Keyword</h2>
                    <MainPageTabs />
                </S.Body>
            </S.BodySection>
            <Footer />
        </S.Main>
    )

}
