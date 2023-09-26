import React, { useState } from 'react';
import axios from 'axios';

function NewsPreview() {
  const [previewData, setPreviewData] = useState(null);

  const fetchPreview = async (url) => {
    // 여기서 url은 미리보기 정보가 필요한 웹사이트 주소입니다.
    // 아래 코드는 예시이며 실제로는 백엔드 서버나 Link Preview API 등을 통해 
    // 데이터를 가져와야 합니다.
    const response = await axios.get(`your_backend_server_or_API/${url}`);
    setPreviewData(response.data);
  };

  return (
    <div>
      <button onClick={() => fetchPreview('https://n.news.naver.com/article/366/0000935186?ntype=RANKING')}>
        Get preview
      </button>
      {previewData && (
        <div>
          <h2>{previewData.title}</h2>
          <p>{previewData.description}</p>
          {previewData.image && <img src={previewData.image} alt="news" />}
        </div>
      )}
    </div>
  );
}

export default NewsPreview;
