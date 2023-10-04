import * as S from "./style";

function Card({ title, img_src, url }) {
  const handleClick = () => {
    window.open(url, '_blank');
  };

  const handleImageError = (e) => {
    e.target.src = "https://img.kbs.co.kr/kbs/620/news.kbs.co.kr/resources/images/topimg/program/0011.jpg";
  };

  console.log(img_src)

  return (
    <S.CardSection onClick={handleClick}>
      <S.TextContainer>
        <h2>{title}</h2>
      </S.TextContainer>
      <img
        src={img_src}
        alt=""
        onError={handleImageError}
      />
    </S.CardSection>
  );
}

export default Card;
