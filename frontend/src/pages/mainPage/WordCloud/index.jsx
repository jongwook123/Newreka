import React, { useEffect, useRef, useState } from 'react';
import * as d3 from 'd3';
import cloud from 'd3-cloud';

function WordCloudPage({ onWordClick, data }) {

  function handleCircleClick(event, d) {
    const targetElement = document.getElementById('body2');
    if (targetElement) {
      targetElement.scrollIntoView({ behavior: 'smooth' });
    }

    onWordClick(d.text);
  }
  const wordRef = useRef(null);
  const [keywords, setKeywords] = useState();
  const [dummydata, setDummydata] = useState({
    '0': 2000,
    '1': 2000,
    '2': 2000,
    '3': 2000,
    '4': 2000,
    '5': 5000,
    '6': 6000,
    '7': 7000,
    '8': 8000,
    '9': 15000,
  });

  useEffect(() => {
    setKeywords(data["quizList"]);
  }, [data]);

  useEffect(() => {
    if (data && data.quizList && data.quizList.length > 9) {
      let newDummyData = {};
      for (let i = 0; i < 10; i++) {
        if (data.quizList[i]) {
          newDummyData[data.quizList[i].name] = i < 5 ? 2000 : (i + 1) * 1000;
        }
      }
      setDummydata(newDummyData);
    }
  }, [data]);
  
  useEffect(() => {
    if (keywords){
    if (!wordRef.current) return;

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
      d3.select(wordRef.current).selectAll('*').remove();
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

      words.forEach(d => {
        d.z = Math.sqrt(d.size);
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
        .attr('font-family', "pretendard")
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
        .attr('y', (d, i) => i * 15)
        .text(d => d)
        .on('click', function (event, d) {
          const targetElement = document.getElementById('body2');
          if (targetElement) {
            targetElement.scrollIntoView({ behavior: 'smooth' });
          }
        });
    }

    draw(words);
  }
  }, [dummydata]);

  return <div ref={wordRef} />;
}

export default WordCloudPage;
