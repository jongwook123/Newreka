import * as S from './style';
import { useState } from 'react';
import NewsPreview from 'component/previews';
import WideCard from 'component/newscards/widecard';


const MyPageTabs = () => {
    const [activeTabIndex, setActiveTabIndex] = useState(0);

    const handleTabSelect = (index) => {
        setActiveTabIndex(index);
    };

    return (
        <S.CustomTabs onSelect={handleTabSelect} selectedIndex={activeTabIndex}>
            <S.CustomTabList>
                <S.CustomTab>전체</S.CustomTab>
                <S.CustomTab>경제</S.CustomTab>
                <S.CustomTab>정치</S.CustomTab>
                <S.CustomTab>연예</S.CustomTab>
                <S.CustomTab>사회</S.CustomTab>
                <S.CustomTab>IT</S.CustomTab>
                <S.CustomTab>세계</S.CustomTab>
            </S.CustomTabList>

            <S.CustomTabPanel>
                {/* <NewsPreview /> */}
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                <WideCard props={{
                    title: `장학금 받고 이공계 이탈 '먹튀'…1천200일 넘게 미납도
`, img_src: "https://imgnews.pstatic.net/image/001/2023/09/26/PCM20220103000176990_P4_20230926060154706.jpg?type=w647"
                }} />
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                <WideCard props={{
                    title: `장학금 받고 이공계 이탈 '먹튀'…1천200일 넘게 미납도
`, img_src: "https://imgnews.pstatic.net/image/001/2023/09/26/PCM20220103000176990_P4_20230926060154706.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING"
                }} />
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                <WideCard props={{
                    title: `장학금 받고 이공계 이탈 '먹튀'…1천200일 넘게 미납도
`, img_src: "https://imgnews.pstatic.net/image/001/2023/09/26/PCM20220103000176990_P4_20230926060154706.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING"
                }} />
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
                <WideCard props={{ title: `“고향 가느니 용돈 벌래요”…황금연휴, 최대 200만원 단기 일자리 찾는 청년들`, img_src: "https://imgnews.pstatic.net/image/366/2023/09/26/0000935186_001_20230926061101337.jpg?type=w647", url: "https://n.news.naver.com/article/001/0014219992?ntype=RANKING" }} />
            </S.CustomTabPanel>
        </S.CustomTabs>
    );
};

export default MyPageTabs;