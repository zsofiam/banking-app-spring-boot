import Header from "./header";
import Footer from "./footer";

export default function Layout({
                                   title = "Title",
                                   description = "Description",
                               }) {
    return (
        <div>
            <Header />
            <main style={{ padding: "1rem" }}>
                <h2>{title}</h2>
                <p>{description}</p>
            </main>

            <Footer />
        </div>
    );
}