import * as S from './style';
import { useState } from 'react';
import WideCard from 'component/newscards/widecard';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { TryGetAllScrapped } from 'APIs/ArticleAPIs';


const MyPageTabs = () => {
    const [activeTabIndex, setActiveTabIndex] = useState(0);
    const [scrapData, setScrapData] =useState([])
    const handleTabSelect = (index) => {
        setActiveTabIndex(index);
    };
    
    const accessToken = useSelector((state) => state.user.accessToken);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await TryGetAllScrapped(accessToken);
                setScrapData(result.scrapList)
            } catch (error) {
                console.error('Error fetching keyword count:', error);
            }
        };

        fetchData();
    }, [accessToken,scrapData]);
    return (
        <S.CustomTabs onSelect={handleTabSelect} selectedIndex={activeTabIndex}>
            <S.CustomTabList>
                <S.CustomTab>전체</S.CustomTab>
                <S.CustomTab>오피니언</S.CustomTab>
                <S.CustomTab>세계</S.CustomTab>
                <S.CustomTab>생활</S.CustomTab>
                <S.CustomTab>사회</S.CustomTab>
                <S.CustomTab>경제</S.CustomTab>
                <S.CustomTab>정치</S.CustomTab>
                <S.CustomTab>IT</S.CustomTab>
            </S.CustomTabList>

            <S.CustomTabPanel>
                {scrapData.map((scrapItem) => (
                    <WideCard
                        props={{
                            title: scrapItem.article.title,
                            img_src: scrapItem.article.thumbnail === "NaN"
                                ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                : scrapItem.article.thumbnail,
                            url: scrapItem.article.link,
                        }}
                    />
                ))}
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                    .filter(scrapItem => scrapItem.article.category === "오피니언")
                    .map((scrapItem, index) => (
                        <WideCard
                            key={index}
                            props={{
                                title: scrapItem.article.title,
                                img_src: scrapItem.article.thumbnail === "NaN"
                                    ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                    : scrapItem.article.thumbnail,
                                url: scrapItem.article.link,
                            }}
                        />
                    ))
                }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                    .filter(scrapItem => scrapItem.article.category === "세계")
                    .map((scrapItem, index) => (
                        <WideCard
                            key={index}
                            props={{
                                title: scrapItem.article.title,
                                img_src: scrapItem.article.thumbnail === "NaN"
                                    ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                    : scrapItem.article.thumbnail,
                                url: scrapItem.article.link,
                            }}
                        />
                    ))
                }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                        .filter(scrapItem => scrapItem.article.category === "생활")
                        .map((scrapItem, index) => (
                            <WideCard
                                key={index}
                                props={{
                                    title: scrapItem.article.title,
                                    img_src: scrapItem.article.thumbnail === "NaN"
                                        ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                        : scrapItem.article.thumbnail,
                                    url: scrapItem.article.link,
                                }}
                            />
                        ))
                    }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                        .filter(scrapItem => scrapItem.article.category === "사회")
                        .map((scrapItem, index) => (
                            <WideCard
                                key={index}
                                props={{
                                    title: scrapItem.article.title,
                                    img_src: scrapItem.article.thumbnail === "NaN"
                                        ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                        : scrapItem.article.thumbnail,
                                    url: scrapItem.article.link,
                                }}
                            />
                        ))
                    }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                        .filter(scrapItem => scrapItem.article.category === "경제")
                        .map((scrapItem, index) => (
                            <WideCard
                                key={index}
                                props={{
                                    title: scrapItem.article.title,
                                    img_src: scrapItem.article.thumbnail === "NaN"
                                        ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                        : scrapItem.article.thumbnail,
                                    url: scrapItem.article.link,
                                }}
                            />
                        ))
                    }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                        .filter(scrapItem => scrapItem.article.category === "정치")
                        .map((scrapItem, index) => (
                            <WideCard
                                key={index}
                                props={{
                                    title: scrapItem.article.title,
                                    img_src: scrapItem.article.thumbnail === "NaN"
                                        ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                        : scrapItem.article.thumbnail,
                                    url: scrapItem.article.link,
                                }}
                            />
                        ))
                    }
            </S.CustomTabPanel>
            <S.CustomTabPanel>
                {scrapData
                        .filter(scrapItem => scrapItem.article.category === "IT")
                        .map((scrapItem, index) => (
                            <WideCard
                                key={index}
                                props={{
                                    title: scrapItem.article.title,
                                    img_src: scrapItem.article.thumbnail === "NaN"
                                        ? "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72"
                                        : scrapItem.article.thumbnail,
                                    url: scrapItem.article.link,
                                }}
                            />
                        ))
                    }
            </S.CustomTabPanel>
        </S.CustomTabs>
    );
};

export default MyPageTabs;