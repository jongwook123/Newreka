import * as S from "./style";

function Card({ props: { title, img_src } }) {

  return (
    <S.CardSection>
      <S.TextContainer>
        <h2>{title}</h2>
      </S.TextContainer>
      <img
        src={img_src}
        alt=""
      />
    </S.CardSection>
  );
}

export default Card;
