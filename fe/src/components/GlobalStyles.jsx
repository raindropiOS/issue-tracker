import { createGlobalStyle } from 'styled-components';

const GlobalStyles = createGlobalStyle` 
    a {
      text-decoration: none;
      color: inherit;
    }
    * {
      box-sizing: border-box;
      padding: 0;
      margin: 0;
    }
    button {
      border: none;
      background: none;
      padding: 0;
      cursor: pointer;
      color: inherit;
    }
    input {
      color: inherit;
    }
    input:focus {
      outline:none;
    }
    li{
      list-style: none;
    }
`;

export default GlobalStyles;
