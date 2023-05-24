import styled from 'styled-components';
import { useContext } from 'react';
import { Button } from '../../common';
import { ReactComponent as label } from '../../../assets/label.svg';
import { ReactComponent as mileStone } from '../../../assets/mileStone.svg';
import { ReactComponent as plus } from '../../../assets/plus.svg';
import { MainPageContext } from '../../../context/MainPage/MainPageContext';

const NabTab = () => {
  const { labels, mileStones } = useContext(MainPageContext);

  const totalLabel = labels?.length > 0 ? label.length : 0;
  const totalMileStone = mileStones?.length > 0 ? mileStone.length : 0;

  return (
    <NabTabBox>
      <div>
        <Button
          type="ghostButton"
          size="S"
          gap="4px"
          color="neutralText"
          backgroundColor="neutralBackground"
          hoverColor="neutralTextWeak"
        >
          <LableIcon />
          {`레이블(${totalLabel})`}
        </Button>
        <Button
          type="ghostButton"
          size="S"
          gap="4px"
          color="neutralText"
          backgroundColor="neutralBackground"
          hoverColor="neutralTextWeak"
        >
          <MileStoneIcon />
          {`마일스톤(${totalMileStone})`}
        </Button>
      </div>
      <Button
        type="containerButton"
        size="S"
        gap="7px"
        color="accentText"
        backgroundColor="accentBackground"
        hoverColor="neutralText"
      >
        <PlusIcon />
        이슈작성
      </Button>
    </NabTabBox>
  );
};

export default NabTab;

const NabTabBox = styled.div`
  display: flex;
  gap: 16px;

  > div {
    display: flex;
    justify-content: space-around;

    width: 321px;
    height: 40px;
    border: 1px solid ${({ theme }) => theme.color.neutralBorder};
    border-radius: 11px;

    > button {
      width: 100%;
      border-radius: 11px 0px 0px 11px;
    }

    > button:last-child {
      border-left: 1px solid ${({ theme }) => theme.color.neutralBorder};
      border-radius: 0px 11px 11px 0px;
    }
  }
`;

const LableIcon = styled(label)`
  stroke: ${({ theme }) => theme.color.neutralText};
`;

const MileStoneIcon = styled(mileStone)`
  fill: ${({ theme }) => theme.color.neutralText};
`;

const PlusIcon = styled(plus)`
  stroke: ${({ theme }) => theme.color.accentText};
`;
