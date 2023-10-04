import * as S from './style';

function WideCard({ props: { title, img_src, url } }) {
    const handleClick = () => {
        window.open(url, '_blank');
    };

    return (
        <S.CardSection onClick={handleClick}>
            <img
                src={img_src}
                alt="https://img.kbs.co.kr/kbs/620/news.kbs.co.kr/resources/images/topimg/program/0011.jpg"
            />
            <S.TextContainer>
                <h2>{title}</h2>
            </S.TextContainer>
        </S.CardSection>
    )
}

export default WideCard;
