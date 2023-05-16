import { styled } from 'styled-components';
import { UserIcon } from '../UserIcon/UserIcon';
import logoMedium from '../../../assets/LogotypeMedium.svg';
import userImageLarge from '../../../assets/userImageLarge.svg';

export const Header = () => {
  return (
    <HeaderBox>
      <img src={logoMedium} alt="medium logo" />
      <UserIcon imgSrc={userImageLarge} large />
    </HeaderBox>
  );
};

const HeaderBox = styled.header`
  display: flex;
  justify-content: space-between;

  padding: 27px 80px;
`;
