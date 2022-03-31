import { Link } from "react-router-dom";
export default function Header() {
    return (
        <nav
            style={{
                borderBottom: "solid 1px",
                paddingBottom: "1rem",
            }}
        >
            <Link to="/">Home</Link> | <Link to="/about">About</Link> |{" "}
            <Link to="/contacts">Contacts</Link>
        </nav>
    );
}
