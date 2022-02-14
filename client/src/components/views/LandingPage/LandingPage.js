// rfce
import axios from 'axios';
import React from 'react';
// import anime from 'animejs/lib/anime.es.js'
import { anime } from 'react-anime';
import { Link } from 'react-router-dom';
import './LandingPage.css'

function LandingPage(props) {

  const items = document.querySelectorAll('.nav--header-1 > .nav__item');
  const rootElement = document.querySelector('.layout');

  const colors = [
    'hsla(14, 97%, 65%, 0.4)',
  ];

  // Local state.
  const state = {
    navigationItems: {},
    root: rootElement,
  };

  for (let navItemIndex = 0; navItemIndex < items.length; ++navItemIndex) {
    const stateItem = {
      color: colors[navItemIndex % colors.length],
      element: items[navItemIndex],
      id: navItemIndex,
      isActive: false,
      type: 'DEFAULT',
    } 
    
    const subNav =  items[navItemIndex].querySelector('.nav');
    if (subNav) {
      // current element has a subNav.
      stateItem.childNavigation = subNav;
      stateItem.type = 'PARENT';
    }
    
    stateItem.onClick = (event) => {
      const actualOnClick = () => {
        if (state.activeItem === navItemIndex) {
          return;
        }
        
        if (state.activeItem) {
          state.activeItem = null;
        } 

        if ('PARENT' === state.navigationItems[navItemIndex].type) {
          // Set new active item.
          state.activeItem = navItemIndex;

          animateShow(state);
        }
      };
      
      if (state.activeItem) {
        return animateHide(state, actualOnClick);
      }
      
      return actualOnClick();
    };
    
    // Add this item to the state.
    state.navigationItems[navItemIndex] = stateItem;
  }

  const animateShow = (state) => {
    const animation = anime.timeline();
    console.log(state.navigationItems[state.activeItem]);
    
    animation.add({
      backgroundColor: state.navigationItems[state.activeItem].color,
      begin: () => {
        state.root.classList.add('nav--active');
      },
      complete: () => {
        state.navigationItems[state.activeItem].element.classList.add('nav__item--active');
      },
      duration: 450,
      easing: 'easeOutExpo',
      opacity: 1,
      translateX: [
        {delay: 300, value: '270px',},
      ],
      scaleX: [
        {value: 0},
        {value: 1},
      ],
      targets: '.layout__frontdrop',
    })
    .add({
      duration: 70,
      opacity: [0, 1],
      targets: state.navigationItems[state.activeItem].childNavigation,
    }).add({
      delay: anime.stagger(70),
      opacity: [0, 1],
      translateY: ['100%', '0'],
      targets: state.navigationItems[state.activeItem].childNavigation.querySelectorAll('.nav__item'),
    });
    
    return animation;
  };

  const animateHide = (state, complete) => {
    const animation = anime.timeline({
      complete: complete,
    });
    
    animation.add({
      duration: 210,
      opacity: [1, 0],
      translateY: [0, '+=50px'],
      targets: state.navigationItems[state.activeItem].childNavigation,
    }).add({
      complete: () => {
        // Clean-up current active item.
        state.root.classList.remove('nav--active');
        state
          .navigationItems[state.activeItem]
          .element
          .classList
          .remove('nav__item--active')
        ;
      },
      duration: 250,
      easing: 'easeOutCirc',
      scaleX: [
        {value: 0,},
      ],
      translateX: [
        {value: 0},
      ],
      targets: '.layout__frontdrop',
    });
    
    return animation;
  };

  (() => {
    // Ready to fight.
    const introAnimation = anime.timeline({
      complete: () => {
        for (let stateItemIndex = 0; stateItemIndex < Object.values(state.navigationItems).length; ++ stateItemIndex) {
          state.navigationItems[stateItemIndex].element.addEventListener(
            'click',
            state.navigationItems[stateItemIndex].onClick
          );
          
          // Reset transform to prevent the implicit z-index / position relative to trigger.
          state.navigationItems[stateItemIndex].element.style.transform = '';
        }
      },
    });

    introAnimation.add({
      duration: 350,
      delay: 1000,
      easing: 'easeOutCirc',
      targets: '.layout__backdrop',
      scaleX: [0, 1],
    }).add({
      delay: anime.stagger(75),
      duration: 450,
      easing: 'easeOutCirc',
      opacity: [0, 1],
      translateY: ['100%', '0%'],
      targets: '.nav--header-1 > .nav__item:not(.nav__item--home)',
    }).add({
      easing: 'easeOutExpo',
      targets: '.layout__backdrop',
      translateX: [
        {delay: 350, value: (67) + '%'},
      ],
    }).add({
      duration: 350,
      easing: 'easeOutExpo',
      targets: '.hero-title',
      opacity: [0, 1],
      translateY: ['50px', '0'],
    }).add({
      duration: 350,
      easing: 'easeOutExpo',
      targets: '.hero-text',
      opacity: [0, 1],
      translateY: ['0', '-3rem'],
    }, '-=100');
  })();

  return (
    <div 
    // style={{
    //   display:'flex', justifyContent: 'center', alignItems: 'center'
    //   ,width: '100%', height: '100vh'
    // }}
    >
      <div className="layout">
  <div className="layout__backdrop"></div>
  <div className="layout__frontdrop"></div>
  <div className="layout__wrapper">
    <header className="layout__header">
      <nav>
        <ul className="nav nav--header nav--header-1">
          <li className="nav__item nav__item--home">
            <a className="nav__link" href="#0">Home</a>
          </li>
          <li className="nav__item nav__item--about">
            <Link className="nav__link" to="/main">Home</Link>
          </li>
          <li className="nav__item nav__item--clients">
            <a className="nav__link" href="#0">Study</a>
            <ul className="nav nav--header nav--header-2">
              <li className="nav__item">
                <a className="nav__link" href="#0">Burger King</a>
              </li>
              <li className="nav__item">
                <a className="nav__link" href="#0">Southwest Airlines</a>
              </li>
              <li className="nav__item">
                <a className="nav__link"  href="#0">Levi Strauss</a>
              </li>
            </ul>
          </li>
          <li className="nav__item nav__item--services">
            <a className="nav__link" href="#0">Game</a>
            <ul className="nav nav--header nav--header-2">
              <li className="nav__item">
                <a className="nav__link" href="#0">Print Design</a>
              </li>
              <li className="nav__item">
                <a className="nav__link" href="#0">Web Design</a>
              </li>
              <li className="nav__item">
                <a className="nav__link" href="#0">Mobile App Development</a>
              </li>
            </ul>
          </li>
          {/* <li className="nav__item nav__item--contact">
            <a className="nav__link" href="#0">Contact</a>
          </li> */}
        </ul>
      </nav>
    </header>
    <main className="layout__main">
      <section className='hero'>
        <div className="hero-block hero-block--header">
          <h1 className="hero-title">
            Fork It <em>Navigation with Sub-navigation, a Codepen Challenge, Aug 2019.</em>
            
          </h1>
          <p className="hero-text">The idea was to fork <a href="https://codepen.io/pen?template=PMbwKb" target="_blank">a pretty basic navigation</a> and make something nice with it. The content inspired me some kind of advertising adgency with a bold navigation and a little content. Made me work on my hover and active state more than what I'm used to in those weekly challenges.</p>
        </div>
      </section>
    </main>
  </div>
</div>
      {/* <button onClick={onClickHandler}>
        로그아웃
      </button> */}
    </div>
  );
}

export default LandingPage;
