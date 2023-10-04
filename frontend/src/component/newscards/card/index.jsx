import React, { useState } from 'react';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import * as S from './style';

function Card({ props: { title, img_src } }) {
  const [isScrapped, setIsScrapped] = useState(false);

  const handleToggleScrap = () => {
    setIsScrapped((prev) => !prev);

  };

  return (
    <S.CardSection>
      <img src={img_src} alt="" />
      <S.TextContainer>
        {title}
      </S.TextContainer>
      <S.ToggleButton onClick={handleToggleScrap}>
        {isScrapped ? <BookmarkIcon /> : <BookmarkBorderIcon />}
      </S.ToggleButton>
    </S.CardSection>
  );
}

export default Card;
