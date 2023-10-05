import React, { useState } from 'react';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import * as S from './style';
import { useSelector } from 'react-redux';
import { TryScrapped } from 'APIs/ArticleAPIs';



function Card({ title, img_src, url, quiz }) {
  const accessToken = useSelector((state) => state.user.accessToken);
  
  const handleClick = () => {
    window.open(url, '_blank');
  };

  const handleImageError = (e) => {
    e.target.src = "https://imgnews.pstatic.net/image/origin/119/2023/10/04/2754807.jpg?type=nf106_72";
  };

  const [isScrapped, setIsScrapped] = useState(false);
  
  const handleToggleScrap = async (event) => {
    event.stopPropagation(); // Stop the event from propagating to the parent element (CardSection)

    // Check if it's not already scrapped
    if (!isScrapped) {
      try {
        // Fetch the quiz information associated with the card
        const result = await TryScrapped(accessToken,quiz.keyWordId,quiz.articleId);
        // Check the result if scrap was successful
        if (result.message === 'success') {
          setIsScrapped(true); // Update the state to indicate it's scrapped
        } 
      } catch (error) {
        console.error('Error while attempting to scrap:', error);
      }
    }
  };

  return (
    <S.CardSection onClick={handleClick}>
      <img
        src={img_src}
        alt=""
        onError={handleImageError}
        style={{ maxWidth: '100%', height: 'auto' }}
      />
      <S.TextContainer>
        {title}
      </S.TextContainer>
      <S.ToggleButton onClick={(event) => handleToggleScrap(event, quiz)}>
        {isScrapped ? <BookmarkIcon /> : <BookmarkBorderIcon />}
      </S.ToggleButton>
    </S.CardSection>
  );
}

export default Card;
