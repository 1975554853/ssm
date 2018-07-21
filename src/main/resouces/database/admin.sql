-- 创建触发器
create trigger updateUserType before update on usertb for each row

  begin

    declare user_wrong int;
    select new.userPassWrongCout into user_wrong for update ;
    if user_wrong >= 5 then
      set new.userIsLockout = 1;
    end if ;
  end;
drop trigger updateUserType;

-- 创建存储过程
 CREATE PROCEDURE in_param(IN p_in int)
    BEGIN
    SELECT p_in;
    SET p_in=2;
    SELECT p_in;
    END;
    //
DELIMITER ;
#调用
SET @p_in=1;
CALL in_param(@p_in);
SELECT @p_in;