import * as S from "./style";

function Card({ props: { groupName, img_src } }) {

  return (
    <S.CardSection>
      <img
        src={img_src}
        alt=""
      />
      <S.TextContainer>
        <h2>{groupName}</h2>
      </S.TextContainer>
    </S.CardSection>
  );
}

export default Card;
