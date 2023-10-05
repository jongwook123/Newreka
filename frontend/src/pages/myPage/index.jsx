import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import Keywords from './keywords';
import Scrap from './scrap';
import { useLocation } from 'react-router';
import { useEffect } from 'react';

export default function MyPage() {
    const location = useLocation();

    useEffect(() => {
        if (location.state?.scrollToTop) {
            window.scrollTo(0, 0);
        }
    }, [location]);
    return (
        <S.Main>
            <Header>
            </Header>
            <S.BodySection>
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
            </S.BodySection>
            <Footer />
        </S.Main>
    )

}