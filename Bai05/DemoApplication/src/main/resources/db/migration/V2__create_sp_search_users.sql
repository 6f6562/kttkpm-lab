CREATE PROCEDURE sp_search_users
    @keyword NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;
    SELECT id, name, email
    FROM users
    WHERE LOWER(name) LIKE N'%' + LOWER(LTRIM(RTRIM(@keyword))) + N'%';
END;
