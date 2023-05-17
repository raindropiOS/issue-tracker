import { styled } from 'styled-components';
import search from '../../../assets/search.svg';

const FilterInput = () => {
  return (
    <FilterInputBox>
      <img src={search} alt="search" />
      <input placeholder="Search all issues" />
    </FilterInputBox>
  );
};

export default FilterInput;

const FilterInputBox = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
  flex-grow: 1;

  background: ${({ theme }) => theme.color.neutralBackgroundBold};
  border-radius: 0px 11px 11px 0px;
  padding: 6px 24px;

  input {
    font-weight: ${({ theme }) => theme.fontWeight.regular};
    width: 400px;
    border: none;
    font-size: ${({ theme }) => theme.fontSize.M.size};
    line-height: ${({ theme }) => theme.fontSize.M.lineHeight};
  }
`;
