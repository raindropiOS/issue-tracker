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
  display: flex;
  flex-direction: column;
  gap: 24px;

  padding: 32px 80px;
`;
