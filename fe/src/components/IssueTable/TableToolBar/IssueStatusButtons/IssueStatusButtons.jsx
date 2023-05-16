import styled from 'styled-components';
import alertCircle from '../../../../assets/alertCircle.svg';
import archive from '../../../../assets/archive.svg';
import IssueStatusButton from './IssueStatusButton/IssueStatusButton';

const IssueStatusButtons = ({
  openedIssueCount,
  closedIssueCount,
  isOpened,
  setFilterOptions,
}) => {
  const openIssueButtonHandler = () => {
    setFilterOptions({
      isOpened: true,
    });
  };

  const closeIssueButtonHandler = () => {
    setFilterOptions({
      isOpened: false,
    });
  };

  return (
    <IssueStatusButtonsBox>
      <IssueStatusButton
        buttonText="열린이슈"
        buttonHandler={openIssueButtonHandler}
        count={openedIssueCount}
        buttonStatus={isOpened}
        imgSrc={alertCircle}
      />
      <IssueStatusButton
        buttonText="닫힌이슈"
        buttonHandler={closeIssueButtonHandler}
        count={closedIssueCount}
        buttonStatus={!isOpened}
        imgSrc={archive}
      />
    </IssueStatusButtonsBox>
  );
};

export default IssueStatusButtons;

const IssueStatusButtonsBox = styled.div`
  display: flex;
  flex-grow: 1;
`;
