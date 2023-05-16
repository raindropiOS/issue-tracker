import styled from 'styled-components';

const IssueStatusButton = ({
  buttonText,
  buttonHandler,
  count,
  buttonStatus,
  imgSrc,
}) => {
  return (
    <IssueStatusButtonBox
      type="button"
      onClick={buttonHandler}
      $buttonstatus={buttonStatus}
    >
      <img src={imgSrc} alt={buttonText} />
      {buttonText}
      {`(${count})`}
    </IssueStatusButtonBox>
  );
};

export default IssueStatusButton;

const IssueStatusButtonBox = styled.button`
  display: flex;

  padding-right: 12px;
  font-weight: 700;
  font-size: 16px;
  color: ${(p) => (p.$buttonstatus ? '#14142B' : '#6E7191')};
  cursor: pointer;
  gap: 10px;
`;
