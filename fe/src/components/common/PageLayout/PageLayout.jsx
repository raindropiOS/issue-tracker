import { styled } from 'styled-components';
import { Header } from '../Header/Header';

export const PageLayout = ({ children }) => {
  return (
    <>
      <Header />
      <Content>{children}</Content>
    </>
  );
};

const Content = styled.main`
  padding: 32px 80px;
`;
