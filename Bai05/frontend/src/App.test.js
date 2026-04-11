import { render, screen } from "@testing-library/react";
import App from "./App";

test("renders search heading", () => {
  render(<App />);
  expect(
    screen.getByText(/SQL Server — tìm theo tên/i)
  ).toBeInTheDocument();
});
