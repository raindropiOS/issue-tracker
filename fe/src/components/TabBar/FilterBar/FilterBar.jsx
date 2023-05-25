import { styled } from 'styled-components';
import { useContext } from 'react';
import FilterInput from './FilterInput/FilterInput';
import DropDown from '../../DropDown/DropDown';
import {
  MainPageContext,
  MainPageDispatchContext,
} from '../../../context/MainPage/MainPageContext';
import { setStateFilterOption } from '../../../context/MainPage/MainPageActions';
import { CLOSED, OPENED } from '../../../constants';

const FilterBar = () => {
  const { filterOptions } = useContext(MainPageContext);
  const dispatch = useContext(MainPageDispatchContext);

  const dropDownConfig = {
    buttonText: '필터',
    headerText: '이슈 필터',
    bodyItems: [
      {
        text: '열린 이슈',
        checked: filterOptions.state === OPENED,
        handleItemClick: () => dispatch(setStateFilterOption(OPENED)),
      },
      {
        text: '닫힌 이슈',
        checked: filterOptions.state === 'closed',
        handleItemClick: () => dispatch(setStateFilterOption(CLOSED)),
      },
    ],
    bodyCheck: true,
    marginTop: '10px',
  };

  return (
    <FilterBarBox>
      <FilterBarDropDown config={dropDownConfig} />
      <FilterInput filterOptions={filterOptions} />
    </FilterBarBox>
  );
};
export default FilterBar;

const FilterBarBox = styled.div`
  display: flex;

  border: 1px solid ${({ theme }) => theme.color.neutralBorder};
  border-radius: 11px;
  width: 600px;
  position: relative;
  height: 40px;
  color: ${({ theme }) => theme.color.neutralTextWeak};
`;

const FilterBarDropDown = styled(DropDown)`
  padding: 4px 24px;
  border-radius: 11px 0px 0px 11px;
`;
