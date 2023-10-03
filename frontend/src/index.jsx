import React from 'react';
import { createRoot } from 'react-dom/client';
import { Provider } from 'react-redux';
import store from './redux/store'; // 실제 Redux store 경로에 따라 수정
import App from './App';

const root = document.getElementById('root');

if (root !== null) {
  const hydrate = root.hasChildNodes();
  const rootContainer = createRoot(root, {hydrate});

  rootContainer.render(
    <Provider store={store}>
      <App />
    </Provider>
  );
}
