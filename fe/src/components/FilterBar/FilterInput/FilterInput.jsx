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
  justify-content: center;
  align-items: center;
  gap: 8px;
  flex-grow: 1;

  background: #eff0f6;
  border-radius: 0px 11px 11px 0px;
  padding: 6px 24px;

  input {
    font-weight: 400;
    width: 400px;
    background-color: inherit;
    border: none;
    font-size: 16px;
    line-height: 28px;
  }
`;
