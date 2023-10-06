import Header from 'component/header';
import Footer from 'component/footer';
import * as S from './style';
import Keywords from './keywords';
import Scrap from './scrap';
import { useEffect } from 'react';

export default function MyPage() {

    // 렌더링시 스크롤 최상단
    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);

    return (
        <S.Main>
            <Header menuname={"toggle"} />
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