import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import WordCloudPage from './WordCloud';
import MainPageTabs from 'component/tabs/mainPageTabs';

export default function MainPage() {
    return (
        <S.Main>
            <Header menu_name="My Page">
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
