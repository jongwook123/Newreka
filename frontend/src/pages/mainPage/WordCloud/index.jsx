import React, { useEffect, useRef } from 'react';
import * as d3 from 'd3';
import cloud from 'd3-cloud';

const dummydata = {
  '바위': 2000,
  '귤': 2000,
  '이재명 구속': 2000,
  '윤우혁 다이아': 2000,
  '천병찬...': 2000,
  '이걸 올라가?': 5000,
  '6일 황금연휴': 6000,
  '박종욱<<<<': 7000,
  '에메랄드 =': 8000,
  '짤리려나?': 15000,
};

function handleCircleClick(event, d) {
  console.log(d.text);
  
  // Scroll to the target section (Body2 in this case)
  const targetElement = document.getElementById('body2');
  if (targetElement) {
    targetElement.scrollIntoView({ behavior: 'smooth' });
  }
}


function WordCloudPage() {
  const wordRef = useRef(null);

  useEffect(() => {
    const words = Object.entries(dummydata)
      .sort(([, a], [, b]) => b - a)
      .map(([text, size]) => ({ text: text, size: size }));

    const layout = cloud()
      .size([750, 650])
      .words(words)
      .rotate(() => ~~(3000))
      .fontSize(d => Math.sqrt(d.size))
      .on('end', draw);

    layout.start();

    function draw(words) {
      const top5Words = words.slice(0, 5); // Select top 5 words
      const svgWidth = layout.size()[0];
      const svgHeight = layout.size()[1];
      const svgCenterX = svgWidth / 2;
      const svgCenterY = svgHeight / 2;
      let group = d3
        .select(wordRef.current)
        .append('svg')
        .attr('width', layout.size()[0])
        .attr('height', layout.size()[1])
        .append('g')
        .attr('transform', `translate(${svgCenterX},${svgCenterY})`);

      // Modify position based on size (z-index based on size)
      words.forEach(d => {
        d.z = Math.sqrt(d.size); // Adjust the function based on your preference
        d.maxFontSize = Math.min(24, Math.max(10, (Math.abs(d.size) / 2.5))); // Limit the max font size to 24
      });
      const colorScale = d3.scaleOrdinal()
        .domain(top5Words.map(d => d.text))
        .range(d3.schemeCategory10);

      const simulation = d3.forceSimulation(words)
        .force('charge', d3.forceManyBody().strength(100))
        .force('collide', d3.forceCollide().radius(d => Math.max(10, Math.abs(d.size * 0.95)))) // Adjust the additional radius
        .stop();

      for (let i = 0; i < 5000; ++i) simulation.tick();
      group
        .selectAll('.word-circle')
        .data(words)
        .enter()
        .append('circle')
        .attr('r', d => Math.max(10, Math.abs(d.size)))
        .attr('class', 'word-circle')
        .attr('transform', d => `translate(${d.x},${d.y})`)
        .attr('fill', d => (top5Words.includes(d) ? colorScale(d.text) : 'gray'))
        .attr('opacity', 0.7)
        .attr('z', d => d.z)
        .on('click', handleCircleClick);

      group
        .selectAll('word-text')
        .data(words)
        .enter()
        .append('text')
        .attr('font-size', d => Math.sqrt(d.size) * 2.5)
        .style('fill', 'white')
        .attr('font-family',"pretendard")
        .attr('text-anchor', 'middle')
        .attr('class', 'text')
        .attr('transform', d => `translate(${d.x},${d.y})`)
        .attr('z', d => d.z)
        .attr('cursor', 'default')
        .selectAll('tspan')
        .data(d => d.text.split(/(\s+)/))
        .enter()
        .append('tspan')
        .attr('x', 0)
        .attr('y', (d, i) => i * 15)  // Adjust the line height as needed
        .text(d => d)
        .on('click', function(event, d) {
          // 이곳에 클릭 이벤트 핸들러를 작성
          console.log(d);
          // 원하는 동작을 추가하세요
          const targetElement = document.getElementById('body2');
          if (targetElement) {
            targetElement.scrollIntoView({ behavior: 'smooth' });
          }
        });

      // Append circles after text, so they visually appear on top
    }

    // Function to generate a random color
    function getRandomColor() {
      const letters = '0123456789ABCDEF';
      let color = '#';
      for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
      }
      return color;
    }

  }, []);

  return <div ref={wordRef} />;
}

export default WordCloudPage;
