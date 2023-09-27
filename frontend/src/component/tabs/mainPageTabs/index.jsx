import * as S from './style';
import { useState } from 'react';
import NewsPreview from 'component/previews';
import Card from 'component/newscards/card';
import Quizzes from 'pages/mainPage/Quizzes';



const MainPageTabs = () => {
    const [activeTabIndex, setActiveTabIndex] = useState(0);

    const handleTabSelect = (index) => {
        setActiveTabIndex(index);
    };

    return (
        <S.CustomTabs onSelect={handleTabSelect} selectedIndex={activeTabIndex}>
            <S.CustomTabList>
                <S.CustomTab>뉴스 3줄 요약</S.CustomTab>
                <S.CustomTab>관련 뉴스</S.CustomTab>
                <S.CustomTab>문제 풀기</S.CustomTab>
            </S.CustomTabList>

            <S.CustomTabPanel>
            </S.CustomTabPanel>
            <S.CustomTabPanel>

                <S.CardList>
                    <S.CardItem>
                        <Card props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                    </S.CardItem>
                    <S.CardItem>
                        <Card props={{
                            title: `장학금 받고 이공계 이탈 '먹튀'…1천200일 넘게 미납도
`, img_src: "https://imgnews.pstatic.net/image/001/2023/09/26/PCM20220103000176990_P4_20230926060154706.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING"
                        }} />
                    </S.CardItem>
                    <S.CardItem>
                        <Card props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                    </S.CardItem>
                    <S.CardItem>
                        <Card props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                    </S.CardItem>
                    <S.CardItem>
                        <Card props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                    </S.CardItem>
                </S.CardList>
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                <S.QuizSection>
<<<<<<< HEAD
                    <Quizzes />
=======
                    <Quiz/>
>>>>>>> 9b820e46c445909dfc1750aaaf21f0d30eae3649
                </S.QuizSection>
            </S.CustomTabPanel>
        </S.CustomTabs>
    );
};

export default MainPageTabs;