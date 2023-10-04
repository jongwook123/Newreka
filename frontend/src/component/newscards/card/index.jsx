import React, { useState } from 'react';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import * as S from './style';


function Card({ title, img_src, url }) {
  const handleClick = () => {
    window.open(url, '_blank');
  };

  const handleImageError = (e) => {
    e.target.src = "https://img.kbs.co.kr/kbs/620/news.kbs.co.kr/resources/images/topimg/program/0011.jpg";
  };

  const [isScrapped, setIsScrapped] = useState(false);

  const handleToggleScrap = () => {
    setIsScrapped((prev) => !prev);

  };

  return (
    <S.CardSection onClick={handleClick}>
      <img
        src={img_src}
        alt=""
        onError={handleImageError}
      />
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
