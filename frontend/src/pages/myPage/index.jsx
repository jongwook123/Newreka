import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import Keywords from './keywords';
import Scrap from './scrap';

export default function MyPage() {
    return (
        <S.Main>
            <Header>
            </Header>
            <S.Body>
                <h2>Keywords</h2>
                <S.KeywordSection>
                    <Keywords />
                </S.KeywordSection>
            </S.Body>
            <S.Body>
                <h2>Scrapped News</h2>
                <Scrap />
            </S.Body>
            <Footer />
        </S.Main>
    )

}