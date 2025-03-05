import Form from "@/components/dashboard/Form.jsx";
import Header from "@/layouts/Header";

function Dashboard() {
  return (
    <>
      <Header />
      <div className="mt-16">
        <Form />
      </div>
      {/* <Rooms /> */}
    </>
  );
}

export default Dashboard;
